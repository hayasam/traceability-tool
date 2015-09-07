package br.edu.ufcg.splab.util;

public enum ExecutionMode {
	
	FULL{
		public String toString(){
			return "full";
		}
	},
	PARTIAL {
		public String toString(){
			return "partial";
		}
	};
	
	public static ExecutionMode getEnum(String value) {
        for(ExecutionMode v : values()) {
        	
        	System.out.println(v.toString());
        	
            if(v.toString().equalsIgnoreCase(value)) {
            	return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
