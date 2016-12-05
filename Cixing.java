package edu.thu.yichengqi.attribute;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.openide.util.Lookup;





public class Cixing {
	
	public double CalculateFirst (String JudgeStandard){
		int AllChar=0;
		int Number=0;
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getModel();
		Graph graph = graphModel.getGraph();
		String status = (String) graph.getNode(1).getAttributes().getValue("status");
		String regex1 = "//@+?.:";
		String regex2 = "//@+?.：";
		status = status.replaceAll(regex1, "");
		status = status.replaceAll(regex2, "");	
		List<Term> terms = ToAnalysis.parse(status);
		new NatureRecognition(terms).recognition(); //词性标注
		for (int k=0; k<terms.size();k++){
			if (terms.get(k).toString().contains("/"))
			{
				AllChar++;
			}
			if (terms.get(k).toString().contains(JudgeStandard))
			{
				Number++;
			}
		}		
		return (double)Number/AllChar;
	}
	public double CalculateFirst (String JudgeStandard1,String JudgeStandard2){
		int AllChar=0;
		int Number=0;
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getModel();
		Graph graph = graphModel.getGraph();
		String status = (String) graph.getNode(1).getAttributes().getValue("status");
		String regex1 = "//@+?.:";
		String regex2 = "//@+?.：";
		status = status.replaceAll(regex1, "");
		status = status.replaceAll(regex2, "");	
		List<Term> terms = ToAnalysis.parse(status);
		new NatureRecognition(terms).recognition(); //词性标注
		for (int k=0; k<terms.size();k++){
			if (terms.get(k).toString().contains("/"))
			{
				AllChar++;
			}
			if (terms.get(k).toString().contains(JudgeStandard1))
			{
				Number++;
			}
			if (terms.get(k).toString().contains(JudgeStandard2))
				Number--;
		}		
		return (double)Number/AllChar;
	}
	public double CalculateAll (String JudgeStandard){
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getModel();
		Graph graph = graphModel.getGraph();
		int NodeNumber = graphModel.getGraph().getNodeCount();
		
		String regex1 = "//@.+?:";
		String regex2 = "//@.+?：";
		int Ai = 0;
		String StatusArray[] = new String [NodeNumber];
		for (Node node : graph.getNodes()) {
			String status = (String) node.getAttributes().getValue("status");			
			if (status==null||status.length()<=0)
			{	
				status = "";
			}			
			status = status.replaceAll(regex1, "");
			status = status.replaceAll(regex2, "");				
			StatusArray[Ai] = status;
			Ai++;
		}
		int Number = 0;
		int AllChar = 0;
		for (int i=0;i<NodeNumber;i++){
			List<Term> terms = ToAnalysis.parse(StatusArray[i]);
			new NatureRecognition(terms).recognition(); //词性标注
			for (int k=0; k<terms.size();k++){
				if (terms.get(k).toString().contains("/"))
				{
					AllChar++;
				}
				if (terms.get(k).toString().contains(JudgeStandard))
				{
					Number++;
				}
			}
		}
		if (Number==0)
		{
			return 0;
		}
		else
		{
			return (double) Number/AllChar;
		}
	}
	public double CalculateAll (String JudgeStandard1,String JudgeStandard2){
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getModel();
		Graph graph = graphModel.getGraph();
		int NodeNumber = graphModel.getGraph().getNodeCount();
		
		String regex1 = "//@.+?:";
		String regex2 = "//@.+?：";
		int Ai = 0;
		String StatusArray[] = new String [NodeNumber];
		for (Node node : graph.getNodes()) {
			String status = (String) node.getAttributes().getValue("status");			
			if (status==null||status.length()<=0)
			{	
				status = "";
			}			
			status = status.replaceAll(regex1, "");
			status = status.replaceAll(regex2, "");				
			StatusArray[Ai] = status;
			Ai++;
		}
		int Number = 0;
		int AllChar = 0;
		for (int i=0;i<NodeNumber;i++){
			List<Term> terms = ToAnalysis.parse(StatusArray[i]);
			new NatureRecognition(terms).recognition(); //词性标注
			for (int k=0; k<terms.size();k++){
				if (terms.get(k).toString().contains("/"))
				{
					AllChar++;
				}
				if (terms.get(k).toString().contains(JudgeStandard1))
				{
					Number++;
				}
				if (terms.get(k).toString().contains(JudgeStandard2))
				{
					Number--;
				}
			}
		}
		if (Number==0)
		{
			return 0;
		}
		else
		{
			return (double) Number/AllChar;
		}
	}
	public double CalculateEntropy (String JudgeStandard){	
		
			GraphModel graphModel = Lookup.getDefault()
					.lookup(GraphController.class).getModel();
			Graph graph = graphModel.getGraph();
			
			int NodeNumber = graphModel.getGraph().getNodeCount();
			
			String regex1 = "//@.+?:";
			String regex2 = "//@.+?：";
			int Ai = 0;
			String StatusArray[] = new String [NodeNumber];
			for (Node node : graph.getNodes()) {
				String status = (String) node.getAttributes().getValue("status");			
				if (status==null||status.length()<=0)
				{	
					status = "";
				}			
				status = status.replaceAll(regex1, "");
				status = status.replaceAll(regex2, "");				
				StatusArray[Ai] = status;
				Ai++;
			}
			
			
			
			int Number = 0;
			int AllChar = 0;
			double AllP = 0;
			for (int i=0;i<NodeNumber;i++){
				List<Term> terms = ToAnalysis.parse(StatusArray[i]);
				new NatureRecognition(terms).recognition(); //词性标注
				AllChar=0;
				Number =0;
				for (int k=0; k<terms.size();k++){
					if (terms.get(k).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(k).toString().contains(JudgeStandard))
					{
						Number++;
					}
				}
				if (AllChar==0)
					AllChar=1;
				AllP += (double)Number/AllChar; 
			}
		
			
			
			double P=0;
			if (AllP==0){
				P=0;
			}
			else{
			for (int j=0;j<StatusArray.length;j++){
				double ThisP = 0;
				AllChar=0;
				int ThisNumber=0;
				List<Term> terms = ToAnalysis.parse(StatusArray[j]);
				new NatureRecognition(terms).recognition(); //词性标注
				for (int n=0; n<terms.size();n++){			
					if (terms.get(n).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(n).toString().contains(JudgeStandard))
						ThisNumber++;
				}
					if (AllChar==0)
						ThisP=0;
					else{
					ThisP = (double)ThisNumber/AllChar/AllP;
					}
					if (ThisP==0)
						ThisP = 1;
					P += - (ThisP * Math.log(ThisP)/Math.log(2));
					}
			}
			return P;			
		}
	public double CalculateEntropy (String JudgeStandard1,String JudgeStandard2){	
		
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getModel();
		Graph graph = graphModel.getGraph();
		
		int NodeNumber = graphModel.getGraph().getNodeCount();
		
		String regex1 = "//@.+?:";
		String regex2 = "//@.+?：";
		int Ai = 0;
		String StatusArray[] = new String [NodeNumber];
		for (Node node : graph.getNodes()) {
			String status = (String) node.getAttributes().getValue("status");			
			if (status==null||status.length()<=0)
			{	
				status = "";
			}			
			status = status.replaceAll(regex1, "");
			status = status.replaceAll(regex2, "");				
			StatusArray[Ai] = status;
			Ai++;
		}
		
		
		
		int Number = 0;
		int AllChar = 0;
		double AllP = 0;
		for (int i=0;i<NodeNumber;i++){
			List<Term> terms = ToAnalysis.parse(StatusArray[i]);
			new NatureRecognition(terms).recognition(); //词性标注
			AllChar=0;
			Number =0;
			for (int k=0; k<terms.size();k++){
				if (terms.get(k).toString().contains("/"))
				{
					AllChar++;
				}
				if (terms.get(k).toString().contains(JudgeStandard1))
				{
					Number++;
				}
				if(terms.get(k).toString().contains(JudgeStandard2))
				{
					Number--;
				}
			}
			if (AllChar==0)
				AllChar=1;
			AllP += (double)Number/AllChar; 
		}
	
		
		
		double P=0;
		if (AllP==0){
			P=0;
		}
		else{
		for (int j=0;j<StatusArray.length;j++){
			double ThisP = 0;
			AllChar=0;
			int ThisNumber=0;
			List<Term> terms = ToAnalysis.parse(StatusArray[j]);
			new NatureRecognition(terms).recognition(); //词性标注
			for (int n=0; n<terms.size();n++){			
				if (terms.get(n).toString().contains("/"))
				{
					AllChar++;
				}
				if (terms.get(n).toString().contains(JudgeStandard1))
					ThisNumber++;
				if (terms.get(n).toString().contains(JudgeStandard2))
					ThisNumber--;
			}
				if (AllChar==0)
					ThisP=0;
				else{
				ThisP = (double)ThisNumber/AllChar/AllP;
				}
				if (ThisP==0)
					ThisP = 1;
				P += - (ThisP * Math.log(ThisP)/Math.log(2));
				}
		}
		return P;			
	}

		public double CalculateStandardDeviation (String JudgeStandard){			
			GraphModel graphModel = Lookup.getDefault()
						.lookup(GraphController.class).getModel();
			Graph graph = graphModel.getGraph();
			String regex1 = "//@.+?:";
			String regex2 = "//@.+?：";
			int Ai = 0;
			int NodeNumber = graphModel.getGraph().getNodeCount();
			String StatusArray[] = new String [NodeNumber];
			for (Node node : graph.getNodes()) {
				String status = (String) node.getAttributes().getValue("status");			
				if (status==null||status.length()<=0)
				{	
					status = "";
				}			
				status = status.replaceAll(regex1, "");
				status = status.replaceAll(regex2, "");				
				StatusArray[Ai] = status;
				Ai++;
			}
			int Number = 0;
			int AllChar = 0;
			double AllP = 0;
			for (int i=0;i<NodeNumber;i++){
				List<Term> terms = ToAnalysis.parse(StatusArray[i]);
				new NatureRecognition(terms).recognition(); //词性标注
				AllChar=0;
				Number =0;
				for (int k=0; k<terms.size();k++){
					if (terms.get(k).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(k).toString().contains(JudgeStandard))
					{
						Number++;
					}
				}
				if (AllChar==0)
					AllChar=1;
				AllP += (double)Number/AllChar; 
			}
			double Average = AllP/NodeNumber;
			double SD=0;
			if(AllP==0)
				SD=0;
			else{
				for (int j=0;j<StatusArray.length;j++){
				double ThisP = 0;
				AllChar=0;
				int ThisNumber=0;
				List<Term> terms = ToAnalysis.parse(StatusArray[j]);
				new NatureRecognition(terms).recognition(); //词性标注
				for (int n=0; n<terms.size();n++){
					if (terms.get(n).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(n).toString().contains(JudgeStandard)){
						ThisNumber++;
					}
				}
					if (AllChar==0)
						AllChar=1;
					ThisP = (double)ThisNumber/AllChar/AllP;
					SD += Math.pow((ThisP-Average), 2);	
				}
				SD = Math.sqrt((SD/NodeNumber));
			}
				return SD;
			}
		
		public double CalculateStandardDeviation (String JudgeStandard1,String JudgeStandard2){			
			GraphModel graphModel = Lookup.getDefault()
						.lookup(GraphController.class).getModel();
			Graph graph = graphModel.getGraph();
			String regex1 = "//@.+?:";
			String regex2 = "//@.+?：";
			int Ai = 0;
			int NodeNumber = graphModel.getGraph().getNodeCount();
			String StatusArray[] = new String [NodeNumber];
			for (Node node : graph.getNodes()) {
				String status = (String) node.getAttributes().getValue("status");			
				if (status==null||status.length()<=0)
				{	
					status = "";
				}			
				status = status.replaceAll(regex1, "");
				status = status.replaceAll(regex2, "");				
				StatusArray[Ai] = status;
				Ai++;
			}
			int Number = 0;
			int AllChar = 0;
			double AllP = 0;
			for (int i=0;i<NodeNumber;i++){
				List<Term> terms = ToAnalysis.parse(StatusArray[i]);
				new NatureRecognition(terms).recognition(); //词性标注
				AllChar=0;
				Number =0;
				for (int k=0; k<terms.size();k++){
					if (terms.get(k).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(k).toString().contains(JudgeStandard1))
					{
						Number++;
					}
					if (terms.get(k).toString().contains(JudgeStandard2))
					{
						Number--;
					}
				}
				if (AllChar==0)
					AllChar=1;
				AllP += (double)Number/AllChar; 
			}
			double Average = AllP/NodeNumber;
			double SD=0;
			if(AllP==0)
				SD=0;
			else{
				for (int j=0;j<StatusArray.length;j++){
				double ThisP = 0;
				AllChar=0;
				int ThisNumber=0;
				List<Term> terms = ToAnalysis.parse(StatusArray[j]);
				new NatureRecognition(terms).recognition(); //词性标注
				for (int n=0; n<terms.size();n++){
					if (terms.get(n).toString().contains("/"))
					{
						AllChar++;
					}
					if (terms.get(n).toString().contains(JudgeStandard1)){
						ThisNumber++;
					}
					if (terms.get(n).toString().contains(JudgeStandard2)){
						ThisNumber--;
					}
				}
					if (AllChar==0)
						AllChar=1;
					ThisP = (double)ThisNumber/AllChar/AllP;
					SD += Math.pow((ThisP-Average), 2);	
				}
				SD = Math.sqrt((SD/NodeNumber));
			}
				return SD;
			}
			
			public static void main(String[] args) {
				
			LoadGraph.loadGraph("data/3801407440123708.gexf");
			Cixing cixing = new Cixing();	
			System.out.println("英文词的熵值是"+cixing.CalculateEntropy("/en")+"，标准差是"+cixing.CalculateStandardDeviation("/en"));
			System.out.println("名词的熵值是"+cixing.CalculateEntropy("/n")+"，标准差是"+cixing.CalculateStandardDeviation("/n"));
			System.out.println("动词的熵值是"+cixing.CalculateEntropy("/v")+"，标准差是"+cixing.CalculateStandardDeviation("/v"));
			System.out.println("时间词的熵值是"+cixing.CalculateEntropy("/t")+"，标准差是"+cixing.CalculateStandardDeviation("/t"));
			System.out.println("形容词的熵值是"+cixing.CalculateEntropy("/a")+"，标准差是"+cixing.CalculateStandardDeviation("/a"));
			System.out.println("叹词的熵值是"+cixing.CalculateEntropy("/e","/en")+"，标准差是"+cixing.CalculateStandardDeviation("/e","/en"));
			System.out.println("代词的熵值是"+cixing.CalculateEntropy("/r")+"，标准差是"+cixing.CalculateStandardDeviation("/r"));
			System.out.println("所处词的熵值是"+cixing.CalculateEntropy("/s")+"，标准差是"+cixing.CalculateStandardDeviation("/s"));
			System.out.println("方向词的熵值是"+cixing.CalculateEntropy("/f")+"，标准差是"+cixing.CalculateStandardDeviation("/f"));
			System.out.println("区别词的熵值是"+cixing.CalculateEntropy("/b")+"，标准差是"+cixing.CalculateStandardDeviation("/b"));
			System.out.println("状态词的熵值是"+cixing.CalculateEntropy("/z")+"，标准差是"+cixing.CalculateStandardDeviation("/z"));
			System.out.println("数词的熵值是"+cixing.CalculateEntropy("/m")+"，标准差是"+cixing.CalculateStandardDeviation("/m"));
			System.out.println("量词的熵值是"+cixing.CalculateEntropy("/q")+"，标准差是"+cixing.CalculateStandardDeviation("/q"));
			System.out.println("副词的熵值是"+cixing.CalculateEntropy("/d")+"，标准差是"+cixing.CalculateStandardDeviation("/d"));
			System.out.println("介词的熵值是"+cixing.CalculateEntropy("/p")+"，标准差是"+cixing.CalculateStandardDeviation("/p"));
			System.out.println("连词的熵值是"+cixing.CalculateEntropy("/c")+"，标准差是"+cixing.CalculateStandardDeviation("/c"));
			System.out.println("助词的熵值是"+cixing.CalculateEntropy("/u")+"，标准差是"+cixing.CalculateStandardDeviation("/u"));
			System.out.println("语气词的熵值是"+cixing.CalculateEntropy("/y")+"，标准差是"+cixing.CalculateStandardDeviation("/y"));
			System.out.println("拟声词的熵值是"+cixing.CalculateEntropy("/o")+"，标准差是"+cixing.CalculateStandardDeviation("/o"));
			System.out.println("前缀的熵值是"+cixing.CalculateEntropy("/h")+"，标准差是"+cixing.CalculateStandardDeviation("/h"));
			System.out.println("后缀的熵值是"+cixing.CalculateEntropy("/k")+"，标准差是"+cixing.CalculateStandardDeviation("/k"));
			System.out.println("标点符号的熵值是"+cixing.CalculateEntropy("/w")+"，标准差是"+cixing.CalculateStandardDeviation("/w"));
			}
		}


