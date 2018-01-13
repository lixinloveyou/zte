package lxspider;
//实现队列的数据结构
import java.util.LinkedList;

public class Queue {

	private LinkedList<Object> queue = new LinkedList<Object>();
	public void enQueue(Object t)
	{
		queue.addLast(t);
	}
	public Object deQueue()
	{
		return queue.removeFirst();
	}
	public boolean isQueueEmpty()
	{
		return queue.isEmpty();
	}
	public boolean contains(Object t)
	{
		return queue.contains(t);
	}
	public boolean empty()
	{
		return queue.isEmpty();
	}
}
