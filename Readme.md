# PrestaShop DEMO

### Тесты написанны с использованием следующих технологий:
 - Java
 - TestNG
 - Selenium
 - Hamcrest
 - Owner
 - Allure

### Запуск тестов

- ##### Maven

Данную команду нужно выполнить в папке проекта.
```
mvn clean test site
```
Но прежде чем ее выполнять, необходимо изменить класс `InitialConfiguration.java`. Точнее необходимо изменить следующую строку кода `driver = new RemoteWebDriver(new URL(System.getenv("urlGrid")), cap);` в этой строке нужно указать свой адрес, по которому расположен Selenium Grid, Selenoid или другой сервер который будет запускать браузеры для автотестов.
К примеру:
```
driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/wd/hub"), cap);
```

- ##### Docker 

Чтобы запустить тесты и Selenoid на которых будут выполняться тесты, необходимо выполнить следующую команду:
```
docker-compose up
```
Файл `docker-compose.yml` запускает: 
 - Selenoid - запускает контейнер, который в свою очередь, запускает браузеры в контейнерах для выполнения тестов
 - Selenoid-UI - запускает контейнер с помощью которого можно просматривать выполнение тестов в контейнере
 - bogdan2641/test_prestashop_demo - запускает контейнер с тестами

Файл `docker-compose.yml` имеет следующе содержание:
```
version: '3'
services:
  selenoid:
    network_mode: bridge
    image: aerokube/selenoid:latest
    container_name: "selenoid"
    volumes:
      - "[path_to_browsers.json]:/etc/selenoid"
      - "/var/run/docker.sock:/var/run/docker.sock"
      - "[path_to_videos]:/opt/selenoid/video"
    environment:
      - OVERRIDE_VIDEO_OUTPUT_DIR=[path_to_videos]
    ports:
      - "4444:4444"
    command: ["-conf", "/etc/selenoid/browsers.json", "-video-output-dir", "/opt/selenoid/video", "-video-recorder-image", "selenoid/video-recorder:6.2", "-limit", "20", "-listen", "0.0.0.0:4444"]
  selenoid-ui:
    network_mode: bridge
    image: aerokube/selenoid-ui:latest
    container_name: "selenoid-ui"
    ports:
      - "8100:8100"
    command: ["-selenoid-uri", "[url_of_selenoid]", "-listen", "0.0.0.0:8100"]
  test:
    network_mode: bridge
    image: bogdan2641/test_prestashop_demo
    container_name: "test-prestashop"
    volumes:
      - "[path_to_videos]:/video/"
      - "[path_to_allure_report]:/PrestaShop_Demo/target/allure-report/"
    environment:        
      - urlGrid=[url_of_selenoid_hub]
    command: mvn test site -DnumberSuites=[number_of_suites_1_or_2]
```
[path_to_browsers.json] - путь к файлу `browsers.json` на хост машине. В котором находятся конфигурации браузеров.
[path_to_videos] - путь к папке на хост машине в которую Selenoid будет сохранять видео тестовых прогонов.
[url_of_selenoid] - адрес по которому Selenoid-UI должен подключится к Selenoid. К примеру: `http://192.168.1.101:4444`.
[url_of_selenoid_hub] - адрес по которому находится хаб, к которому тесты должны подключатся. К примеру: `http://192.168.1.101:4444/wd/hub`.
[path_to_allure_report] - путь к папке на хост машине в которой сохраниться allure-report после тестовых прогонов.
[number_of_suites_1_or_2] - количество одновременно выполняющихся test-suite. Значение устанавливается 1 или 2, если значение установленно 1, то сначала выполниться один test-suite потом следующий test-suite, если же значение будет установленно 2 тогда два test-suite будут выполняться параллельно.

К примеру
```
version: '3'
services:
  selenoid:
    network_mode: bridge
    image: aerokube/selenoid:latest
    container_name: "selenoid"
    volumes:
      - "D:/Project/config:/etc/selenoid"
      - "/var/run/docker.sock:/var/run/docker.sock"
      - "D:/Project/video:/opt/selenoid/video"
    environment:
      - OVERRIDE_VIDEO_OUTPUT_DIR=D:/Project/video
    ports:
      - "4444:4444"
    command: ["-conf", "/etc/selenoid/browsers.json", "-video-output-dir", "/opt/selenoid/video", "-video-recorder-image", "selenoid/video-recorder:6.2", "-limit", "20", "-listen", "0.0.0.0:4444"]
  selenoid-ui:
    network_mode: bridge
    image: aerokube/selenoid-ui:latest
    container_name: "selenoid-ui"
    ports:
      - "8100:8100"
    command: ["-selenoid-uri", "http://192.168.1.101:4444", "-listen", "0.0.0.0:8100"]
  test:
    network_mode: bridge
    image: bogdan2641/test_prestashop_demo
    container_name: "test-prestashop"
    volumes:
      - "D:/Project/video/:/video/"
      - "D:/Project/allure/:/PrestaShop_Demo/target/allure-report/"
    environment:        
      - urlGrid=http://192.168.1.101:4444/wd/hub  
    command: mvn test site -DnumberSuites=2
```

Содержание файла `browsers.json`:
```
{
    "chrome": {
        "default": "87.0",
        "versions": {
            "87.0": {
                "image": "selenoid/vnc_chrome:87.0",
                "port": "4444",
                "path": "/"
            },
            "86.0": {
                "image": "selenoid/vnc_chrome:86.0",
                "port": "4444",
                "path": "/"
            }
        }
    },
    "firefox": {
        "default": "83.0",
        "versions": {
            "83.0": {
                "image": "selenoid/vnc_firefox:83.0",
                "port": "4444",
                "path": "/wd/hub"
            },
            "82.0": {
                "image": "selenoid/vnc_firefox:82.0",
                "port": "4444",
                "path": "/wd/hub"
            }
        }
    }
}
```

Файл `browsers.json` описывает на каких браузерах будут выполняться тесты. Перед запуском Selenoid необходимо вручную скачать образы браузеров, на основании этих образов Selenoid создаст контейнеры с этими браузерами.

### Просмотр отчета Allure
Чтобы просмотреть отчет от allure нужно установить [allure-commandline](https://www.npmjs.com/package/allure-commandline) и выполнить следующую команду:
```
allure open [path_to_folder_with_report]
```
path_to_folder_with_report - путь к папке с отчетом
К примеру:
```
allure open /target/allure-report/
allure open D:/allure_report/
```
