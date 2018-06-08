package de.htwsaar.esch.vs1.soap.geoip;

import java.util.ArrayList;

public class Launcher
{
  public static void main( String[] args )
  {
    GeoIPServiceSoap ipService = new GeoIPService().getGeoIPServiceSoap();
    
    ArrayList<GeoIP> ips = new ArrayList<>();
    ips.add(ipService.getGeoIP("134.96.210.150")); //htw saar
    ips.add(ipService.getGeoIP("94.100.251.10")); //telekom.de
    ips.add(ipService.getGeoIP("52.57.211.238")); //bundesregierung.de
    ips.add(ipService.getGeoIP("17.178.96.59")); //apple.com
    for(GeoIP ip: ips){
    	System.out.println( "IP-Adresse "+ip.ip+" kommt aus " +ip.getCountryCode()  );
    }
    
  }
}