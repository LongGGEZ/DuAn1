/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.model.DonHang;
import java.util.ArrayList;


/**
 *
 * @author hoang
 */
public class DonHangDAO {
     static ArrayList<DonHang> List=new ArrayList<>();
     public static int add(DonHang e){
        List.add(e);
        return 1;
     }
}
