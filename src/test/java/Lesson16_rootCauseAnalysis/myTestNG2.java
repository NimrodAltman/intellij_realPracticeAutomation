package Lesson16_rootCauseAnalysis;

import org.testng.annotations.Test;

public class myTestNG2 {

    @Test(groups = {"test1"})
    public void test01(){
        System.out.println("this is test01 from class mtTestNG2");
    }

    @Test(groups = {"test2"})
    public void test02(){
        System.out.println("this is test02 from class mtTestNG2");
    }

    @Test(groups = {"test3"})
    public void test03(){
        System.out.println("this is test03 from class mtTestNG2");
    }

    @Test(groups = {"test4"})
    public void test04(){
        System.out.println("this is test04 from class mtTestNG2");
    }

    @Test(groups = {"test2", "test3"})
    public void test05(){
        System.out.println("this is test05 from class mtTestNG2");
    }
}
