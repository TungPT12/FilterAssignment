/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author TUNGPT
 */
public class LoadRoadMap implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        if(roadMap==null)
            roadMap = new HashMap<>();
        String realPath = context.getRealPath("/")+"WEB-INF\\roadMap.txt";
        File f = new File(realPath);
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                String[] tmp = sc.next().split("=");
                roadMap.put(tmp[0],tmp[1]);
                context.setAttribute("MAP", roadMap);
            }
        } catch (FileNotFoundException ex) {
            context.log("LoadRoadMap : FileNotFoundException :s " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.removeAttribute("MAP");
    }
}
