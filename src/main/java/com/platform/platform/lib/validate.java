package com.platform.platform.lib;

public class validate {


    public boolean IsValid(String name , String username , String password , String email )
    {
        boolean check = false;

        if(! name.isEmpty() && !name.equals(" ") )
              check = true;
        else
            check = false;

        if(! username.isEmpty() && !username.equals(" ") )
              check = true;
        else
            check = false;

        if(! password.isEmpty() && !password.equals(" ") )
              check = true;
        else
            check = false;

        if(! email.isEmpty() && !email.equals(" ") )
              check = true;
        else
            check = false;

        return check;
    }

}
