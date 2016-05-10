package utilities;
import java.util.EmptyStackException;
import java.util.Stack;

public class UndoRedoManager<T> 
{
	Stack<T> undoStack;
	Stack<T> redoStack;
	
	public boolean canUndo()
	{
		return !this.undoStack.empty();
	}
	
	public boolean canRedo()
	{
		return !this.redoStack.empty();
	}
	
	public void undo() throws EmptyStackException
	{
		if(!this.canUndo())
			throw new EmptyStackException();
		
		T obj = this.undoStack.pop();
		this.redoStack.push(obj);
	}
	
	public void redo() throws EmptyStackException
	{
		if(!this.canRedo())
			throw new EmptyStackException();
		
		T obj = this.redoStack.pop();
		this.undoStack.push(obj);
	}
	
	public void add(T obj)
	{
		this.undoStack.push(obj);
		this.redoStack.clear();
	}
	
	
}
