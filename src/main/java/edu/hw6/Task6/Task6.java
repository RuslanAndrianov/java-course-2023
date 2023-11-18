package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber"})
public class Task6 {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<>();

    private static void fillKnownPorts() {
        KNOWN_PORTS.put(80, "HTTP");
        KNOWN_PORTS.put(21, "FTP");
        KNOWN_PORTS.put(25, "SMTP");
        KNOWN_PORTS.put(22, "SSH");
        KNOWN_PORTS.put(443, "HTTPS");
        KNOWN_PORTS.put(53, "DNS");
        KNOWN_PORTS.put(3306, "MySQL Database");
        KNOWN_PORTS.put(5432, "PostgreSQL Database");
        KNOWN_PORTS.put(3389, "Remote Desktop Protocol");
        KNOWN_PORTS.put(27017, "MongoDB Database");
        KNOWN_PORTS.put(1521, "Oracle Database");
        KNOWN_PORTS.put(138, "Служба датаграмм NetBIOS");
        KNOWN_PORTS.put(139, "Служба сеансов NetBIOS");
        KNOWN_PORTS.put(445, "Microsoft-DS Active Directory");
        KNOWN_PORTS.put(843, "Adobe Flash");
        KNOWN_PORTS.put(1900, "Simple Service Discovery Protocol (SSDP)");
        KNOWN_PORTS.put(3702, "Динамическое обнаружение веб-служб");
        KNOWN_PORTS.put(5353, "Многоадресный DNS");
        KNOWN_PORTS.put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        KNOWN_PORTS.put(17500, "Dropbox");
    }

    public static @NotNull List<Port> scanPorts() {

        fillKnownPorts();
        List<Port> ports = new ArrayList<>();

        for (Map.Entry<Integer, String> serviceInfo : KNOWN_PORTS.entrySet()) {
            try (ServerSocket serverSocket = new ServerSocket(serviceInfo.getKey())) {
                ports.add(new Port("TCP", serviceInfo.getKey(), serviceInfo.getValue()));
            } catch (Exception ignored) { }
            try (DatagramSocket datagramSocket = new DatagramSocket(serviceInfo.getKey())) {
                ports.add(new Port("UDP", serviceInfo.getKey(), serviceInfo.getValue()));
            } catch (Exception ignored) { }
        }

        return ports;
    }

    public static void prettyPrint(@NotNull List<Port> ports) {
        LOGGER.info("Протокол  Порт      Сервис");
        LOGGER.info("--------------------------");
        for (Port port : ports.stream().sorted(Comparator.comparingInt(Port::port)).toList()) {
            LOGGER.info(String.format("%-10s%-10s%-10s", port.protocol(), port.port(), port.service()));
        }
    }
}
