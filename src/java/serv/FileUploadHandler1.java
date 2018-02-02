
package serv;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

 



public class FileUploadHandler1 extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "C:/Users/Nilesh/Desktop/";   

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       
       PrintWriter out=response.getWriter();

        if(ServletFileUpload.isMultipartContent(request)){

            try {

                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                out.println("<br> size = "+multiparts.size());
               
                for(FileItem item : multiparts){

                    if(!item.isFormField()){
                      
                       if(item.getSize()>0) {
                        String name = new File(item.getName()).getName();
                          out.println(name);
                          String uf=UPLOAD_DIRECTORY + name;

                        item.write( new File(uf));
                       } 
                    }
                    
                }

            } catch (Exception ex) {

               out.print("Exception : "+ex);

            }         

          request.getRequestDispatcher("success.jsp").forward(request, response);

        }else{

          out.print("Sorry this Servlet only handles file upload request");

        }

         
        
    }
}
