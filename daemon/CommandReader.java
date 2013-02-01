/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package railway.daemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import railway.support.MethodInvoker;

/**
 * @param <T> 
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class CommandReader<T> implements Runnable {

    private T object;

    /**
     * 
     * @param object
     */
    public CommandReader(T object) {
        this.object = object;
    }

    /**
     * 
     */
    @Override
    public void run() {

        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

        MethodInvoker<T> invoker = new MethodInvoker<T>(object);

        while (true) {
            String[] res;
            try {
                res = bis.readLine().split(" ", 0);
                String methodName = res[0];
                String[] parameters = Arrays.copyOfRange(res, 1, res.length);
                invoker.invokeMethode(methodName, parameters);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
