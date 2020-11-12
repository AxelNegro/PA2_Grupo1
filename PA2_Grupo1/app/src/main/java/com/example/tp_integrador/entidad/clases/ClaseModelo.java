package com.example.tp_integrador.entidad.clases;

import android.app.Activity;

public class ClaseModelo {
    public static ClaseModelo singletonObject;
    /** A private Constructor prevents any other class from instantiating. */

    private Activity baseActivity;

    public ClaseModelo()
    {
    }
    public static synchronized ClaseModelo getSingletonObject()
    {
        if (singletonObject == null)
        {
            singletonObject = new ClaseModelo();
        }
        return singletonObject;
    }


    /**
     * used to clear CommonModelClass(SingletonClass) Memory
     */
    public void clear()
    {
        singletonObject = null;
    }


    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public Activity getbaseActivity()
    {
        return baseActivity;
    }

    public void setbaseActivity(Activity baseActivity)
    {
        this.baseActivity = baseActivity;
    }

}

