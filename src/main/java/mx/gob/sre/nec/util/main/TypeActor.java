package mx.gob.sre.nec.util.main;

public enum TypeActor {
	SOLICITANTE(1),
	PADRE(2),
	MADRE(3);
	private int value;

	private TypeActor(int value){
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}
	
	
}
