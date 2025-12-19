package pl.wszib.edu.duda;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.wszib.edu.duda.configuration.AppConfiguration;
import pl.wszib.edu.duda.core.Core;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hasło usera: " + DigestUtils.md5Hex("user"));
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.run();
    }


}