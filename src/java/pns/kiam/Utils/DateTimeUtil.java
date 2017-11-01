/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author PSEVO tochka
 */
@Stateless
@LocalBean
public class DateTimeUtil {

    public String longToDate(long stamp, String pattern) {//"yyyy.MM.dd G 'at' HH:mm:ss z"
	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	return dateFormat.format(new Date(stamp));
    }

    public String currantMomentToDate(String pattern) {//"yyyy.MM.dd G 'at' HH:mm:ss z"
	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	return dateFormat.format(new Date());
    }

    public String longToSimpleFullDate(long stamp) {//"yyyy.MM.dd G 'at' HH:mm:ss z"
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS z");
	return dateFormat.format(new Date(stamp));
    }

    public String longToSimpleDate(long stamp) {//"yyyy.MM.dd G 'at' HH:mm:ss z"
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
	return dateFormat.format(new Date(stamp));
    }

    public String longToSimpleTime(long stamp) {//"yyyy.MM.dd G 'at' HH:mm:ss z"
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS  z ");
	return dateFormat.format(new Date(stamp));
    }
}
