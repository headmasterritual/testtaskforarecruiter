package Pages;

import helpers.RandomStringGenerator;

public class Page {
    public static void main(String[] args) {
        RandomStringGenerator.RandomString();
        System.out.println("Random String is: " + RandomStringGenerator.RandomString());
    }
}