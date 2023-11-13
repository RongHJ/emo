package com.rhj.emo;

import org.springframework.ui.context.Theme;

import java.util.LinkedList;

/**
 * 测试消息队列
 */
public class testQueue {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue(2);
        //生产者
        for(int i =0;i<3;i++){
            int id= i;
            new Thread(()->{
                queue.write(new Message(id,"消息"+id));
            },"生产者"+i).start();
        }

        Thread.currentThread().sleep(1000);

        new Thread(()->{
            while (true){
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                queue.take();
            }
        },"消费者").start();

    }


}

/**
 * 消息队列
 */
class MessageQueue {
    //队列
    LinkedList<Message> list = new LinkedList();

    //容量
    private int cacpity;

    public MessageQueue(int cacpity) {
        this.cacpity = cacpity;
    }
    //从队列获取消息
    public Message take(){
        synchronized (list){
            while (list.isEmpty()){
                System.out.println("消息队列为空！");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //从头读取
            Message message = list.removeFirst();
            System.out.println("消费消息："+message);
            list.notifyAll();
            return message;
        }
    }
    /**
     * 写入消息
     */
    public void write(Message message){
        synchronized (list){
            while (list.size() == cacpity){
                System.out.println("消息队列已满");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("生产消息："+message);
            list.addLast(message);
            list.notifyAll();
        }
    }


}


class Message{
    private int id;
    private String message;

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}

