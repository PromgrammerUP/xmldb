import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.javachina.xmldb.db.portal.Db;
import org.jdom.JDOMException;

public class Test {
	public static void main(String[] args) {
		
			//String sql = JOptionPane.showInputDialog("请输入sql语句");
			String sql = "delete teacher where name=张四";
			Db db = new Db();
			try {
				db.execute(sql);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
}
