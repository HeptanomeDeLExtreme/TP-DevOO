package xml;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

/**
 * @author H4203
 * 
 */
public class OuvreurDeFichierXML extends FileFilter {// Singleton

    private static OuvreurDeFichierXML instance = null;

    /**
     * Constructeur pas defaut
     */
    private OuvreurDeFichierXML() {
    }

    /**
     * @return OuvreurDeFichierXML instance
     */
    protected static OuvreurDeFichierXML getInstance() {
	if (instance == null)
	    instance = new OuvreurDeFichierXML();
	return instance;
    }

    /**
     * Ouvre un fichier
     * 
     * @param boolean lecture
     * @return File
     * @throws ExceptionXML
     */
    public File ouvre(boolean lecture) throws ExceptionXML {
	int returnVal;
	JFileChooser jFileChooserXML = new JFileChooser();
	jFileChooserXML.setFileFilter(this);
	jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
	if (lecture)
	    returnVal = jFileChooserXML.showOpenDialog(null);
	else
	    returnVal = jFileChooserXML.showSaveDialog(null);
	if (returnVal != JFileChooser.APPROVE_OPTION)
	    throw new ExceptionXML("Probleme a l'ouverture du fichier");
	return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
     */
    @Override
    public boolean accept(File f) {
	if (f == null)
	    return false;
	if (f.isDirectory())
	    return true;
	String extension = getExtension(f);
	if (extension == null)
	    return false;
	return extension.contentEquals("xml");
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    @Override
    public String getDescription() {
	return "Fichier XML";
    }

    /**
     * @param File
     *            f
     * @return String
     */
    private String getExtension(File f) {
	String filename = f.getName();
	int i = filename.lastIndexOf('.');
	if (i > 0 && i < filename.length() - 1)
	    return filename.substring(i + 1).toLowerCase();
	return null;
    }
}