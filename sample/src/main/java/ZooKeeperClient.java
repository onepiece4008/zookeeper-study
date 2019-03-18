import org.apache.zookeeper.*;

/**
 * Created by suchao on 2019/3/18
 */
public class ZooKeeperClient {

    public static void main(String[] args) throws Exception {

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
            }

        };

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, watcher);
        System.out.println("====创建节点");
        zk.create("/captain", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("====查看节点是否安装成功");
        System.out.println(new String(zk.getData("/captain", false, null)));
        System.out.println("====修改节点的数据");
        zk.setData("/captain", "captain2015".getBytes(), -1);
        System.out.println("====查看修改的节点是否成功");
        System.out.println(new String(zk.getData("/captain", false, null)));
//        System.out.println("====删除节点");
//        zk.delete("/captain", -1);
//        System.out.println("====查看节点是否被删除");
//        System.out.println("节点状态：" + zk.exists("/captain", false));

        zk.close();
    }
}