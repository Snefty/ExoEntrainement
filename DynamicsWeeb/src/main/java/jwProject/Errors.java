package jwProject;
import java.util.HashMap;
import java.util.Map;

public class Errors {

	Map<String, String> st = new HashMap<String,String>();

	public Errors() {
	}

	public Map<String, String> getSt() {
		return st;
	}

	public void ajouterErreur(String nom, String desc) {
		st.put(nom, desc);
	}

	public boolean isEmpty() {
		return st.isEmpty();
	}

	public void clear() {
		st.clear();
	}
	
}
