package com.PrestaShop.InitialConfiguration;

import org.openqa.selenium.remote.DesiredCapabilities;

public enum Browsers {
	
	CHROME {
        public DesiredCapabilities getBrowser(){
            return DesiredCapabilities.chrome();
        }
    },
    FIREFOX {
        public DesiredCapabilities getBrowser() {
            return DesiredCapabilities.firefox();
        }
    };

    public DesiredCapabilities getBrowser(){
        return null;
    }
}
