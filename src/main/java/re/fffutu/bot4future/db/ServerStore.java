package re.fffutu.bot4future.db;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class ServerStore {
    public static final String LOGGED_THREADS = "logged_threads";
    public static final String INVITE_CODE = "invite_code";

    public boolean isInSet(long serverId, String setName, String element) {
        Jedis jedis = Database.create();
        boolean isMember = jedis.sismember("server:" + serverId + ":lists:" + setName, element);
        Database.close(jedis);
        return isMember;
    }

    public void addToSet(long serverId, String setName, String element) {
        Jedis jedis = Database.create();
        jedis.sadd("server:" + serverId + ":lists:" + setName, element);
        Database.close(jedis);
    }

    public void removeFromSet(long serverId, String setName, String element) {
        Jedis jedis = Database.create();
        jedis.srem("server:" + serverId + ":lists:" + setName, element);
        Database.close(jedis);
    }

    public Set<String> getSet(long serverId, String setName) {
        Jedis jedis = Database.create();
        Set<String> set = jedis.smembers("server:" + serverId + ":lists:" + setName);
        Database.close(jedis);
        return set;
    }

    public void setAttribute(long serverId, String attributeName, String value) {
        Jedis jedis = Database.create();
        jedis.set("server:" + serverId + ":attibutes:" + attributeName, value);
        Database.close(jedis);
    }

    public String getAttribute(long serverId, String attributeName) {
        Jedis jedis = Database.create();
        String attrib = jedis.get("server:" + serverId + ":attibutes:" + attributeName);
        Database.close(jedis);
        return attrib;
    }
}
