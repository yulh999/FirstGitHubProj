package cn.yzw.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"001","test1");
        HeroNode hero2 = new HeroNode(2,"002","test2");
        HeroNode hero3 = new HeroNode(3,"003","test3");
        HeroNode hero4 = new HeroNode(4,"004","test4");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        System.out.println("---------------");

//      删除
        singleLinkedList.delete(4);
        singleLinkedList.delete(1);
        singleLinkedList.list();

        HeroNode node = findLastIndexNode(singleLinkedList.getHead(),3);
        System.out.println("res="+node+"i love 小李");
//        System.out.println("有效个数为："+getLength(singleLinkedList.getHead()));
//        修改
//        HeroNode newHeroNode = new HeroNode(2,"yu02","update02");
//        singleLinkedList.update(newHeroNode);
//        singleLinkedList.list();
    }

    public static Integer getLength (HeroNode head) {
            if(head.next == null) {
                return 0;
            }
            int length = 0;
            while (head.next != null) {
                length++;
                head = head.next;
            }
            return length;
        }

//    找到倒数第k个节点
        public static HeroNode findLastIndexNode (HeroNode head, Integer index) {
            HeroNode temp = head.next;
        if(temp == null) {
            return null;
        }
        Integer size = getLength(head);
        if(index<0 || index > size) {
            return null;
        }
        for (int i = 0; i < size - index; i++) {
              temp = temp.next;
        }
        return temp;
    }




}

//创建这个类，来管理英雄！
class SingleLinkedList {
    private HeroNode head = new HeroNode(0,"","");


    public HeroNode getHead() {
        return head;
    }

    //  添加节点到单向链表
    public void add (HeroNode hero) {
        HeroNode temp = head;
        while (temp.next!=null){
            temp = temp.next;
        }
        temp.next = hero;
    }

    /**
     * 第二种方式添加英雄，通过编号来添加数据，使这个英雄列表有序
     * 因为头结点不能动，因此我们仍然通过
     * 因为单链表，因为我们找的temp是 位于 添加位置的 前一个节点，否则插入不了
     */
    public void addByOrder (HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到了，就在temp的后面
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;//指针一定要后移，因为要遍历
        }

        if (flag){
            System.out.printf("插入的英雄%d的编号重复\n",heroNode.no);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 通过no 去修改对于no节点的信息
     * @param newHeroNode
     */
    public void update (HeroNode newHeroNode) {
        HeroNode temp = head.next;
        boolean flag = false;
        if(temp == null) {
            System.out.println("链表为空，无法修改操作！");
            return;
        }

        while (temp != null) {
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
            System.out.println("修改完毕！");
        }else {
            System.out.println("没有找到要修改的节点！");
        }
    }

    /**
     * 删除节点 通过no
     * @param no
     */
    public void delete (Integer no) {
        HeroNode temp = head;
        boolean flag = false;
        if(temp.next == null) {
            System.out.println("链表为空，无法删除操作！");
            return;
        }

        while (temp.next != null) {
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.next.next;//垃圾回收机制 会把这个没有任何引用指向的节点 当做垃圾回收掉
        }else {
            System.out.printf("没有找到编号为%d的英雄，删除失败",no);
        }
    }

    //遍历链表list
    public void list () {
        HeroNode temp = head.next;
        if(temp == null) {
            System.out.println("链表为空！");
        }
        while (true) {
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}


class HeroNode {
    public Integer no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个英雄

    public HeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
