package com.nullcognition.effectivejava2.chapter02.pract00;// Created by ersin on 19/06/15

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

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

	public void testDereferencingObjects(){
		// looking into the linked list code from the remove, LinkIterator<ET> implements ListIterator<ET>
		// which is doing the removal and the nullification
		List<Object> list = new LinkedList<Object>();
		Object toRemove = new Object();
		list.add(toRemove);
		list.remove(toRemove);

	}


	// ------------------------------ Implementation ------------------------------

	// memory managed code must null its de-referenced memory
	interface TFactory<T>{
		T T();
		T[] T(int size);
	}

	public class ManagedMemory<T>{
		private static final int SIZE = 3;
		private int nextEmpty = 0;
		T[] array;
		public ManagedMemory(TFactory<T> factoryOfType){
			array = factoryOfType.T(SIZE);
			// Can't instantiate object of type T, so no new T();
		}

		public int capacity(){return SIZE;}
		public int size(){return nextEmpty;}
		// will be +1 the index of the object, when pushing using post, popping use pre index modification

		public void push(T element){
			if(nextEmpty < SIZE){
				array[nextEmpty++] = element;
				return;
			}
			throw new IndexOutOfBoundsException("push error, at capacity");
		}
		public T unSafePop(){
			if(nextEmpty > 0){ return array[--nextEmpty]; }
			throw new IndexOutOfBoundsException();
		}
		public T safePop(){
			if(nextEmpty > 0){
				T temp = array[--nextEmpty]; // object is no longer in memory
				array[nextEmpty] = null;
				return temp;
			}
			throw new IndexOutOfBoundsException();
		}
		// or use weak references, untested
		// a similar concept is shown in item06 with the WeakHashMap<Integer, CallbackListener> and the observer/sub pattern
		// where the de-registration from an object is not needed since the observer will get gc'd if null, and will yield null objects if
		//polled but yet to gc
		WeakReference<T>[] weakReferences = new WeakReference[SIZE];
		public void pushWR(T element){
			if(nextEmpty < SIZE){
				weakReferences[nextEmpty++] = new WeakReference<T>(element); // new weakreference needed as there is no set, since the gc collects these objects, or does it only collect the referent? and pools the WR?
				return;
			}
			throw new IndexOutOfBoundsException("push error, at capacity");
		}
		public T popWR(){
			if(nextEmpty > 0){
				T temp = weakReferences[--nextEmpty].get();
				weakReferences[nextEmpty].clear();
				return temp;
			}
			throw new IndexOutOfBoundsException();
		}
	}

	// ---------- experimentation: try putting the weakReference in the TFactory ----------
	interface WRFactory<T>{
		WeakReference<T> newWeaklyReferenced();
		WeakReference<T>[] newWealkyReferencedArray(int size);
	}

	class TestingWRFactory<TT>{
		public TT popWRObject(WRFactory<TT> wrFactory){ // do we want to be giving the referent type or a WR
			TT temp = wrFactory.newWeaklyReferenced().get();
			wrFactory.newWeaklyReferenced().clear();
			return temp;
		}
		public TT[] WRArray(WRFactory<TT> wrFactory, int size){ // elegant, don't know if this does anything useful...
			List<TT> array = new ArrayList<>(size);
			WeakReference<TT>[] wrA = wrFactory.newWealkyReferencedArray(size);
			for(int i = 0; i < size; i++){
				array.add(i, wrA[i].get());
			}
			return (TT[]) array.toArray();
		}
	} // --------------------------------------------------------------------------------

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
	static{lengthyComputationalValue = 1 + 1 + 1 + 1;}

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

		public Builder(){} // no need to force parameters on constructor, sensible defaults are selected if none of the builder methods are called
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

// Singleton with observer pattern, memory safe with WeakHashMap
enum PubSub{ // default public static final, methods are default public static
	INSTANCE;

	// Question, should the pubHashMap be of Integer(as the hashcode), WeakHashMap<> or of Publisher, WeakHashMap<>
	// find out why the subHashMap uses it for the answer
	static WeakHashMap<Publisher, ArrayList<Subscriber>> pubHashMapOfListOfSubs = new WeakHashMap<>();
	static WeakHashMap<Integer, Subscriber> subHashMap = new WeakHashMap<>();

	void registerSubscriber(Subscriber callback, Publisher publisher){
		int callbackHash = callback.hashCode();
		// has this sub existed before for any other pub
		if(!subHashMap.containsKey(callbackHash)){
			subHashMap.put(callbackHash, callback);
		}
		ArrayList pubsSubArrayList = pubHashMapOfListOfSubs.get(publisher);
		if(!pubsSubArrayList.contains(callback)){
			pubsSubArrayList.add(callback);
		}// when a sub is not referenced by any list, then it will get gc'd
	}

	// no need to deregister, when the value in the callback is null(no longer reachable it gets gc'd)
	void setValues(int value, Publisher publisher){
		for(Subscriber s : pubHashMapOfListOfSubs.get(publisher)){
			s.setValue(value);
		}
	}

	// but you may want to deregister/un sub if you no longer want updates
	void unregisterSubscriber(Subscriber sub, Publisher pub){ // unregister from specified pub
		pubHashMapOfListOfSubs.get(pub).remove(sub);
	}

	void unregisterSubscriber(Subscriber sub){ // unregisters from all pubs
		for(WeakHashMap.Entry<Publisher, ArrayList<Subscriber>> e : pubHashMapOfListOfSubs.entrySet()){
			e.getValue().remove(sub);
		}
	}
}

interface Subscriber{
	void setValue(final int value);
}

interface Publisher{
	void setValues(final int value);
}

class Sub implements Subscriber{

	//	boolean isRegistered = false; // obsolete, deregistration does not unregister, thus you can't know when to toggle this variable
	private int i = 0;
	public int getI(){ return i; }

	@Override
	public void setValue(final int value){i = value;}

	public void registerToPublisher(final Publisher publisher){
		PubSub.INSTANCE.registerSubscriber(this, publisher);
	}

	public void unregisterFromPublisher(final Publisher publisher){
		PubSub.INSTANCE.unregisterSubscriber(this, publisher);
	}

	public void unregisterFromAllPublishers(){ PubSub.INSTANCE.unregisterSubscriber(this);}
}

class Pub implements Publisher{

	@Override
	public void setValues(final int value){
		PubSub.INSTANCE.setValues(value, this);
	}
}

