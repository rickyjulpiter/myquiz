package com.sistempintar.app.myquiz;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrueFalseQuestion {

    int id;
    String trueStatement;
    String falseStatement, falseStatement2, falseStatement3, soal;
    String explanation;

    public static ArrayList<TrueFalseQuestion> parseJSONArray(String jsonObjectArray)
    {
        System.out.println(jsonObjectArray);
        ArrayList<TrueFalseQuestion> questions = new ArrayList<TrueFalseQuestion>();

        try {
            JSONArray jArray = new JSONArray(jsonObjectArray);
            if (jArray != null) {
                JSONObject jObj;
                for (int i = 0; i < jArray.length(); i++) {
                    jObj = (JSONObject)jArray.get(i);

                    TrueFalseQuestion tf = new TrueFalseQuestion();
                    tf.id = Integer.parseInt((String)jObj.get("id"));
                    tf.trueStatement = (String)jObj.get("true_statement");
                    tf.falseStatement = (String)jObj.get("false_statement");
                    tf.falseStatement2 = (String)jObj.get("false_statement2");
                    tf.falseStatement3 = (String)jObj.get("false_statement3");
                    tf.explanation = (String)jObj.get("explanation");
                    tf.soal = (String)jObj.get("soal");

                    questions.add(tf);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
