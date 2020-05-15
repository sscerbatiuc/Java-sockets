/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.javasockets.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author sscerbatiuc
 */
public class UsingURL {

    public static void main(String[] args) throws IOException {
        String adresa = "https://www.oracle.com/";
        if (args.length > 0) {
            adresa = args[0];
        }
        BufferedReader br = null;
        try {
            URL url = new URL(adresa);
            InputStream in = url.openStream();
            br = new BufferedReader(new InputStreamReader(in));
            String linie;
            while ((linie = br.readLine()) != null) {
                System.out.println(linie);
            }
        } catch (MalformedURLException e) {
            System.err.println("URL invalid !\n" + e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
