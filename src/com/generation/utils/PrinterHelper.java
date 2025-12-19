package com.generation.utils;

import com.generation.model.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PrinterHelper
{

    public static void showMainMenu(){
        System.out.println( "┌──────────────────────────────────────────┐" );
        System.out.println( "│      Welcome to StudentGen               │" );
        System.out.println( "├──────────────────────────────────────────┤" );
        System.out.println( "│      Select 1 option:                    │" );
        System.out.println( "│      . 1 Register Student                │" );
        System.out.println( "│      . 2 Find Student                    │" );
        System.out.println( "│      . 3 Grade Student                   │" );
        System.out.println( "│      . 4 Enroll Student to Course        │" );
        System.out.println( "│      . 5 Show Students Summary           │" );
        System.out.println( "│      . 6 Show Courses Summary            │" );
        System.out.println( "│      . 7 Exit                            │" );
        System.out.println( "└──────────────────────────────────────────┘" );
    }

}
