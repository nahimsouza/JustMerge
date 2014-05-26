import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JustMerge {

	/**
	 * @author Nahim
	 * 
	 * Coloque arquivos .properties que deseja mesclar na raiz.
	 * Defina o caminho da pasta (path) e o nome do arquivo de saida (output).
	 * Have fun =D
	 * 
	 */


	public static void main(String[] args) {
		JustMerge m = new JustMerge();
		
		// m.justMergeBundles();
		m.compareTranslatedBundlesAndMerge();
		
	}
	
	public void compareTranslatedBundlesAndMerge(){
		try {
			
			String path = "C:\\Users\\nas2\\Documents\\workspace2\\JustMerge\\src\\";
			String outputNewBundle = "translatedBundles\\MessageBundleServer_es_ES_Translated.properties";
			String outputMissingTranslation = "missingBundles\\MessageBundleServer_es_ES_Missing.properties";
			
			SortedProperties global = new SortedProperties();
			SortedProperties eflowsBundle = new SortedProperties();
			SortedProperties translatedBundle = new SortedProperties();
			SortedProperties missingTranslation = new SortedProperties();
			
		    File inputFile;
		    File outputFile;
		    FileOutputStream out;

		    // loading data:
		    inputFile = new File(path + "globalBundles\\MessageBundleServer_Global.properties");
	    	global.load(new FileInputStream(inputFile));
	    	
	    	inputFile = new File(path + "translatedBundles\\bundle_es_ES.properties");
	    	eflowsBundle.load(new FileInputStream(inputFile));
	    	
	    	
	    	// generating new data for files
	    		    	
	    	for (Object key : global.keySet()) {
				if (eflowsBundle.containsKey(key)){
					System.out.println("Key: " + key + " = " + global.getProperty((String)key));
					translatedBundle.put(key, eflowsBundle.getProperty((String) key));
				} else {
					translatedBundle.put(key, global.getProperty((String) key));
					missingTranslation.put(key, global.getProperty((String) key));
				}
			}
	    	
	    	
	    	// writing data on files:
			outputFile = new File(path + outputNewBundle);
			out = new FileOutputStream(outputFile);
			translatedBundle.store(out, null);
			
			outputFile = new File(path + outputMissingTranslation);
			out = new FileOutputStream(outputFile);
			missingTranslation.store(out, null);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void justMergeBundles(){
		try {
			
			String path = "C:\\Users\\nas2\\Documents\\workspace2\\JustMerge\\src\\";
			String output = "MessageBundleServer_Global.properties";
			//File folder = new File(path);
			//File[] listOfFiles = folder.listFiles();
			SortedProperties properties = new SortedProperties();
		    File inputFile;
		    
//		    for (File file : listOfFiles) {
//		    	if (file.getName().endsWith("MessagesBundle_pt_BR.properties") && !file.getName().equals(output)){
//			    	inputFile = new File(path + file.getName());
//			    	properties.load(new FileInputStream(inputFile));
//			    	
////			    	inputFile = new FileInputStream(path + file.getName());
////			    	properties.load(new InputStreamReader(inputFile,Charset.forName("ISO-8859-1")));
//		    	}
//			}
			
		    inputFile = new File(path + "MessagesBundleServer_pt_BR.properties");
	    	properties.load(new FileInputStream(inputFile));
	    	
	    	inputFile = new File(path + "TaxiMessagesBundleServer_pt_BR.properties");
	    	properties.load(new FileInputStream(inputFile));
		    
			File outputFile = new File(path + output);
			FileOutputStream out = new FileOutputStream(outputFile);
			properties.store(out, null);
			//properties.store(new OutputStreamWriter(out, "ISO-8859-1"), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
