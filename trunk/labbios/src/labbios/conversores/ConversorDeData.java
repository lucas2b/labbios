package labbios.conversores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class ConversorDeData implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        java.sql.Date sqlDate = null;
        Calendar cal = null;
 
        if (value != null && value.length() > 0) {
          DateFormat df = new SimpleDateFormat("dd-MMM-yy");
 
          try {
              Date utilDate = df.parse(value);
              cal = new GregorianCalendar();
              cal.setTime(utilDate);
              sqlDate = new java.sql.Date(utilDate.getTime());
          }
          catch (ParseException e) {
            throw new ConverterException(e);
          }
        }
        return sqlDate;
      }
 
      public String getAsString(FacesContext context, UIComponent component, Object value) {
        String dateString = null;
        if (value != null) {
          if (value instanceof String) {
            dateString = (String) value;
          }
          else {
            DateFormat df = new SimpleDateFormat("dd-MMM-yy");
            if (value instanceof java.sql.Date) {
                Date date = new Date(((java.sql.Date)value).getTime());
                dateString = df.format(date);
            }
          }
        }
         return dateString;
      }
}
