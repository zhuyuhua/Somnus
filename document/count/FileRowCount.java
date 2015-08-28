package count;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class FileRowCount extends Frame
{
	static int spaceLine = 0;
	static int commentLine = 0;
	static int normalLine = 0;
	
	Panel inputPanel = new Panel();
	Panel outputPanel = new Panel();
	Label inputLabel = new Label("请输入代码存放位置：");
	TextField inputTextField = new TextField("E:\\MyPackage",20);
	Button bb = new Button("清零");
	Button yb = new Button("确定");
	Label normal = new Label("代码行数:");
	TextField normaltf = new TextField(3);
	Label comment = new Label("注释行数：");
	TextField commenttf = new TextField(3);
	Label space = new Label("空格行数：");
	TextField spacetf = new TextField(3);
	Label total = new Label("总计行数：");
	TextField totaltf = new TextField(3);

	public void getFileName(String filePath)
	{
		File f = new File(filePath);
		if(!f.isDirectory())//不是目录
		{
			if(f.getName().endsWith(".java"))
			{
				count(f);
			}
		}
		else//是目录
		{
			String []fileList = f.list();
			for(int i=0;i<fileList.length;i++)
			{
				File file = new File(filePath+"\\"+fileList[i]);
				if(!file.isDirectory())//不是目录
				{
					if(file.getName().endsWith(".java"))
					{
						count(file);
					}
				}
				else		//是目录
				{
					getFileName(file.getPath());//注意：不是getname()!
				}
			}
		}
		
	}
	public void count(File f)
	{
		String line = null;
		boolean comment = false;
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(f));
			line = null;
			while((line = br.readLine())!=null)
			{
				line = line.trim();
				if(line.matches("^[[\\s]&&[^\\n]]*$"))  // spaceLine   ?*$
				{
					spaceLine++;
				}
				else if(line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/")))
				{
					commentLine++;
				}
				else if(line.startsWith("/*")&&!line.endsWith("*/"))
				{
					commentLine++;
					comment = true;
				}
				else if((line.endsWith("*/"))&&comment==true)
				{
					commentLine++;
					comment = false;
				}
				else if(comment==true)
				{
					commentLine++;
				}
				else
				{
					normalLine++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		finally
		{
			if(br!=null)
				try 
				{
					br.close();
					br = null;
				} 
			catch (IOException e) {
					
					e.printStackTrace();
				}
		}
	}
	public static void main(String[]args)
	{
		FileRowCount fr = new FileRowCount();
		fr.start();
	}
	void start()
	{
		setSize(200,280);
		setResizable(false);
		setTitle("java代码统计工具");
		inputPanel.add(inputLabel);
		inputPanel.add(inputTextField);
		
		inputPanel.add(yb);
		inputPanel.add(bb);
		outputPanel.setLayout(new GridLayout(4,2));
		outputPanel.add(normal);
		outputPanel.add(normaltf);
		outputPanel.add(comment);
		outputPanel.add(commenttf);
		outputPanel.add(space);
		outputPanel.add(spacetf);
		outputPanel.add(total);
		outputPanel.add(totaltf);
		setLayout(new GridLayout(3,2));
		add(inputPanel,"North");
		add(outputPanel,"South");
		
		setVisible(true);
		addWindowListener(new WindowAdapter()
				{

					public void windowClosing(WindowEvent e) 
					{
						System.exit(0);
					}
			
				});
		yb.addActionListener(new yesActionListener());
		bb.addActionListener(new emptyActionListener());
	}
	class yesActionListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			getFileName(inputTextField.getText());
			normaltf.setText(new Integer(normalLine).toString());
			commenttf.setText(new Integer(commentLine).toString());
			spacetf.setText(new Integer(spaceLine).toString());
			totaltf.setText(new Integer(normalLine+commentLine+spaceLine).toString());
			normalLine=commentLine=spaceLine=0;
		}
	}
	class emptyActionListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			normaltf.setText("");
			commenttf.setText("");
			spacetf.setText("");
			totaltf.setText("");
			normalLine=commentLine=spaceLine=0;
			//inputTextField.setText("");

		}
	}
}
