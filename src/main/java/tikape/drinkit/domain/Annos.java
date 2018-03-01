/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.drinkit.domain;

/**
 *
 * @author laura
 */
public class Annos {

    private Integer rowid;
    private String nimi;

    public Annos(Integer rowid, String nimi) {
        this.rowid = rowid;
        this.nimi = nimi;
    }

    public Integer getId() {
        return rowid;
    }

    public String getNimi() {
        return nimi;
    }
   }

    

