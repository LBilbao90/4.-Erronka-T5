package controlador;

import javax.swing.JOptionPane;

public class BooleanMetodoak {
	
	/**
	 * Entitate bat datu basean egiaztatzeko
	 * @param entitateak entitateen arraya
	 * @param id_ent zein entitate balidatu nahi den
	 * @return topatu bada <b>true</b>, bestela <b> false </b>
	 */
	public boolean entitateBalidatu(String[] entitateak, String id_ent) {
		boolean aurkituta = false;
		for(int i=0;i<entitateak.length && !aurkituta;i++) {
			if(entitateak[i].equals(id_ent)) {
				aurkituta = true;
			}
		}
		return aurkituta;
	}	

	/**
	 * balidatzen du ez NAN zenbakiaren letra
	 * @param nan_balidatzeko Zein NAN balidatuko da
	 * @return letra zuzena bada true, bestela false
	 */
	public boolean nanBalidatu(String nan_balidatzeko) {
		boolean balidoa = false;
		String[] letrak = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		int nan_int = 0;
		try {
			nan_int = Integer.parseInt(nan_balidatzeko.substring(0,8));	
			if(letrak[nan_int%23].equals(nan_balidatzeko.substring(8).toUpperCase())) {
				balidoa=true;
			}else{
				JOptionPane.showMessageDialog(null,"NAN Okerra!","Error!", JOptionPane.ERROR_MESSAGE);			
			}		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"NAN Okerra!","Error!", JOptionPane.ERROR_MESSAGE);			
		}		
		return balidoa;
	}

	/**
	 * Balidatzen du diru katearen formatoa egokia den ala ez
	 * @param dirua balidatuko den dirua
	 * @return balidoa bada true, bestela false
	 */
	public static boolean diruBalidatu(String dirua) {
		boolean balidoa = false;
		try {
			String[] diru_split = dirua.split(",");
			@SuppressWarnings("unused")
			double balidaketa = Double.parseDouble(diru_split[0]);
			if(diru_split.length>1) {
				balidaketa = Double.parseDouble(diru_split[1]);
			}
			if(diru_split.length==1 || (diru_split[1].length()<=2 && diru_split.length<=2)) {
				balidoa = true;				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Datu okerra!","Error!", JOptionPane.ERROR_MESSAGE);		
		}
		return balidoa;
	}
}
