import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());

        HelloWorld bean2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());

        Cat bean3 =
                (Cat) applicationContext.getBean("cat");
        System.out.println(bean.getMessage());

        Cat bean4 =
                (Cat) applicationContext.getBean("cat");
        System.out.println(bean.getMessage());

        boolean helloWorldBeansEqual = (bean == bean2);
        boolean catBeansEqual = (bean3 == bean4);

        System.out.println("Comparison result for HelloWorld beans: " + helloWorldBeansEqual);
        System.out.println("Comparison result for Cat beans: " + catBeansEqual);

    }
}