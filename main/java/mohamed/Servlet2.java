package mohamed;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.*;
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    public Servlet2() {
        super();
    }

    // Factorielle method
    protected  int factorielle(int n) throws FactException {
        int fact = 1;
        if (n < 0) { throw new FactException("La factorielle d'un nombre négatif n'existe pas");}
        else {
        for (int i = 1; i <= n; i++) {
            if (fact > Integer.MAX_VALUE/i) { throw new FactException("Max value dépassée");}
            fact *= i;// fact=fact*i;
        }
        }
        return fact;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        try {
            int m = Integer.parseInt(request.getParameter("n"));
            pw.println("<html> <head> <title> Tableaux des factorielles </title>");
            pw.println("<link rel='stylesheet' href='style2.css'> ");
            pw.println("</head>");
            pw.println(" <center>");
            pw.println("<h4> Tableau des factorielles du nombre  : "+m+"</h4>");
            pw.println("<table border='1' cellpadding='5' cellspacing='0'>");
            pw.println("<tr><th>Nombre</th> <th> factorielle</th> </tr>");
                /*********************************/
          while(m>=0) {
            	try {
            int result = factorielle(m);
            pw.println("<tr><td>" + m + "</td> <td>" + result + "</td></tr>");
            m=m-1;
            }
            catch (FactException e) {
            pw.println("<tr><td>" + m + "</td><td>Erreur :"+ e.getMessage() + "</td></tr>");
            pw.println("</center> </table>");
            break;
            /* pw.println("<button onclick='window.location.href=\"/Servlet2\"'>Retour</button>");
         pw.println("<button>TEst </button>");
          pw.println("<button onclick='Formulaire.html'> Revenir pour calculer autre n </button>");
          pw.println("</table>");
         response.sendRedirect("Formulaire.html"); */
            }
         //  finally {}
        } // end of while 
        } // first try 
        catch (NumberFormatException e) {
        	pw.println(" <br><center><h4>Erreur : Entrée invalide. Veuillez entrer un nombre entier. </h4> </center>");
            }
        finally {
        	pw.println("</table>");
        	pw.println("</center>");
        	pw.println("</Body> </html>");
        	pw.close();
        }
        }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}