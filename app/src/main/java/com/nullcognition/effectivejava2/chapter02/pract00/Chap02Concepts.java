package com.nullcognition.effectivejava2.chapter02.pract00;// Created by ersin on 19/06/15

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Chap02Concepts{

	// ------------------------------ Testing ------------------------------

	public void testOneBeforeMe(){
		OneBeforeMe.insertQuote("john", "my quote is now");
		OneBeforeMe.insertQuote("I have no words to say what I am");
	}
	public void testFighterRobot(){
		FighterRobot fighterRobot = new FighterRobot.Builder().build(); // returns default
		fighterRobot = new FighterRobot.Builder().buildBest();
		fighterRobot = new FighterRobot.Builder().buildBestA();
		fighterRobot = new FighterRobot.Builder().buildWorst();
	}

	public void testSingletonChanger(){
		SingletonChanger sc = SingletonChanger.valueOf(SingletonChanger.INSTANCE.name());
		SingletonChanger.values();
		int i = SingletonChanger.modifier;
		SingletonChanger.INSTANCE.applyModification(SingletonChanger.INSTANCE.getExD());
	}


	// ------------------------------ Implementation ------------------------------

	// static factory methods with internal tracking
	static class OneBeforeMe{
		private OneBeforeMe(){throw new RuntimeException("private instantiation error");}
		private static final HashMap<String, String> nameQuote = new HashMap<>();
		private static final String ANON_NAME = "anon";
		private static long numberOfSubmissions = 0;

		static{nameQuote.put(ANON_NAME, "For why does the mind race when there is no placing?");}

		// insertQuote acts as peek() operation when no input is set, disable the button if there are no characters
		public static String insertQuote(String name, String quote){
			name = name.toLowerCase();
			String toReplace = nameQuote.get(name);
			if(toReplace == null){ toReplace = "You are the first to set the purse!"; }
			if(quote != null){
				nameQuote.put(name, quote);
				++numberOfSubmissions;
			}
			return toReplace;
		}

		// ask for name; if character is entered, switch anon button option to 'Write Quote'
		public static String insertQuote(String anonQuote){
			String toReplace = nameQuote.get(ANON_NAME);
			if(anonQuote != null){
				nameQuote.put(ANON_NAME, anonQuote);
				++numberOfSubmissions;
			}
			return toReplace;
		}
	}

	public static final int EXTERNAL_DEPENDENCY = 1;

	// serializable
	public enum SingletonChanger{
		INSTANCE(EXTERNAL_DEPENDENCY);
		SingletonChanger(int ed){
			exD = applyModification(ed);
		}

		private final int exD;
		public int getExD(){return exD;}

		public static final int modifier = 5;
		public int applyModification(int i){return i + 2 * modifier;}
	}
}

class LengthyCalculations{
	static{
		lengthyComputationalValue = 1 + 1 + 1 + 1;
	}
	// only calculated once and incurs an upfront cost, useful for objects being created rapidly and
	// needing the value
	public static final int lengthyComputationalValue;

	// calculation is differed to time of call,
	{List someList = computeLazyValue();}
	public List computeLazyValue(){
		if(lazyComputationalValue == null){
			lazyComputationalValue = new LinkedList();
			lazyComputationalValue.add("addMe");
		}
		return lazyComputationalValue;
	}
	// statics are initialized to null as per order of appearance, then when a new instance
	// of LengthyCalculation is created, the computeLazyValue() will get called
	private static List lazyComputationalValue;
}

class FighterRobot{

	public static final int DEFAULT_LEGS = 50;
	public static final int DEFAULT_ARMS = 50;
	public static final int DEFAULT_CHASSIS = 50;

	private final int legs;
	private final int arms;
	private final int chassis;

	private FighterRobot(){throw new RuntimeException("private instantiation error");}
	private FighterRobot(Builder builder){
		arms = builder.arms;
		legs = builder.legs;
		chassis = builder.chassis;
	}

	public static class Builder{

		private int legs = DEFAULT_LEGS;
		private int arms = DEFAULT_ARMS;
		private int chassis = DEFAULT_CHASSIS;

		public Builder(){}
		public FighterRobot build(){return new FighterRobot(this);}
		// viable if there are few parameters, better to use pre-set configs
		public FighterRobot buildBest(){return new FighterRobot(new Builder().appendages(99, 99).chassis(99));}
		public FighterRobot buildBestA(){return new FighterRobot(FighterRobotPresets.best());}
		public FighterRobot buildWorst(){return new FighterRobot(FighterRobotPresets.worst());}

		public Builder appendages(int armType, int legType){
			arms = armType;
			legs = legType;
			return this;
		}

		public Builder chassis(int chassisType){
			this.chassis = chassisType;
			return this;
		}

		// pre-sets can be configured below
		private static class FighterRobotPresets{
			private static Builder best(){return new Builder().appendages(99, 99).chassis(99);}
			private static Builder worst(){return new Builder().appendages(0, 0).chassis(0);}
		}
	}
}
