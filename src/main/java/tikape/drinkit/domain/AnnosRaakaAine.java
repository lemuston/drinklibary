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
public class AnnosRaakaAine {
    private Integer rowid;
    private Integer annosId;
    private Integer raakaaineId;
    private Integer jarjestys;
    private Integer maara;
    private String ohje;
            
            

    public AnnosRaakaAine(Integer rowid, Integer annosId, Integer raakaaineId, Integer jarjestys, Integer maara, String ohje) {
        this.rowid = rowid;
        this.annosId = annosId;
        this.raakaaineId = raakaaineId;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
        
    }

    public Integer getId() {
        return rowid;
    }
    
     public Integer getAnnosId() {
        return annosId;
    }
     
      public Integer getRaakaaineId() {
        return raakaaineId;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }
    
     public Integer getMaara() {
        return maara;
    }
    
    public String getOhje() {
        return ohje;
   }
}
