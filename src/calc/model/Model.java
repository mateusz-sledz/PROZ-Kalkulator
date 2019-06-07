package calc.model;

import java.util.List;
import java.util.Objects;

import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;

public class Model {


    public class NumberFormatException extends Exception {

        private static final long serialVersionUID = 2L;

        public NumberFormatException(String error) {
            super(error);
        }
    }

    private JShell jshell;

    public Model() {
        jshell = JShell.create();
    }
    
    /** Metoda wykonująca obliczenia za pomocą jshell API*/
    
    public String calculate(String expression) throws Exception {

        List<SnippetEvent> events = jshell.eval(expression);
        for (SnippetEvent e : events) {
            if (e.causeSnippet() == null) {
                if (e.status() == Snippet.Status.VALID) {
                    if (e.value() != null) {
                        System.out.println(expression + " = " + e.value() + "\n");
                        return e.value();
                    }
                    break;
                }
                throw new Exception("Zły format wejściowy");
            }
        }
        return "0";
    }

    /** Silnia */
    public String factorion(String expression) throws Exception {

        Integer value = 0;
        value = Integer.parseInt(expression);

        String ret;

        if (value < 0) {

            throw new NumberFormatException("Silnia < 0");
        } else if (value == 0 || value == 1) {
            ret = "1";
            return ret;
        }else if (value > 20){
            throw new NumberFormatException("Out of range");
        }

        Long counted = 1L;
        for (; value > 1; value--) {
            counted *= value;
        }
        ret = Objects.toString(counted);
        return ret;
    }
}
