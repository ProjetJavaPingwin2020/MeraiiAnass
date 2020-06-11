/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class ControleSaisie {

    public ControleSaisie() {
    }

    public boolean controleTextFieldVide(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;
    }

    public void effacerControleSaisie(Label textField) {
        textField.setText("");
    }

    public boolean controleTextFieldNonNumerique(TextField textField, String msg, Label errorLabel) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        return false;
    }

    public boolean controleTextFieldOnlyLetters(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();

        boolean valide = true;

        for (int i = 0; i < tab.length; i++) {
            if (Character.isDigit(tab[i]) || tab[i] == '.' || tab[i] == ',' || tab[i] == '-' || tab[i] == '_' || tab[i] == '@') {
                valide = false;
            }
        }

        if (!valide) {
            errorLabel.setText(msg);
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;
    }

    public boolean controleTextFieldChiffres(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText();
        char[] tab = chaine.toCharArray();

        boolean estUnNombre = true;
        for (int i = 0; i < tab.length; i++) {
            if (!Character.isDigit(tab[i])) {
                estUnNombre = false;
            }
        }
        if (!estUnNombre) {
            errorLabel.setText(msg);
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;
    }

    public boolean controleComboBox(ComboBox<String> combo, String msg, Label errorLabel) {
        if (combo.getValue() == null) {
            errorLabel.setText(msg);
            return true;
        }
        return false;
    }

    public boolean controleLabelfile(TextField textfield, String msg, Label errorLabel) {
        if (textfield.getText().equals("Sélectionner un fichier")) {
            errorLabel.setText(msg);
            return true;
        }
        
        return false;
    }

    public boolean controleTextFieldNumerique1(TextField textField, String msg, Label errorLabel) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            

            return true;
        }
        //effacerControleSaisie(errorLabel);
errorLabel.setText(msg);
        return false;
    }

    public boolean controleTextFieldVide1(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;

    }

    public boolean controleTextFieldNonNumerique1(TextField textField, String msg, Label errorLabel) {
        if (textField.getText().matches(".*[a-zA-Z].*")) {
            errorLabel.setText(msg);

            return true;
        }
        effacerControleSaisie(errorLabel);

        return false;
    }

    public static boolean IntegerCheck(String input, int len) {
        try {
            double d = Integer.parseInt(input);
            if (input.length() != len) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public  boolean IntegerCheck(String input) {
        try {
            double d = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean DoubleCheck(String input) {
        try {
            double d = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
     public boolean validateDatePickerdeb(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir une date de début ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
  public boolean validateDatePickerfin(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir la date de fin ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
    public boolean validateDatePickerexp(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("veuillez choisir une date d'expiration ");
            alert.showAndWait();
            return false;
         }
           return true;
        }
}
