package uk.co.thefishlive.http;

public class Main {

    public static void main(String[] args) {
        int port = 80;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new Server(port).run();
    }
}
