package телевизор;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Класс Программа
class Program {
    private String name;
    private double rating;
    private int viewersCount;

    public Program(String name, double rating, int viewersCount) {
        this.name = name;
        this.rating = rating;
        this.viewersCount = viewersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getViewersCount() {
        return viewersCount;
    }

    public void setViewersCount(int viewersCount) {
        this.viewersCount = viewersCount;
    }

    @Override
    public String toString() {
        return "Program{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", viewersCount=" + viewersCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Double.compare(program.rating, rating) == 0 &&
                viewersCount == program.viewersCount &&
                Objects.equals(name, program.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, viewersCount);
    }
}

// Класс Канал
class Channel {
    private String name;
    private int number;
    private Program program;

    public Channel(String name, int number, Program program) {
        this.name = name;
        this.number = number;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", program=" + program +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return number == channel.number &&
                Objects.equals(name, channel.name) &&
                Objects.equals(program, channel.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, program);
    }
}

// Класс Телевизор
class Television {
    private List<Channel> channels;
    private boolean isOn;
    private int currentChannelIndex;

    public Television(List<Channel> channels) {
        this.channels = channels;
        this.isOn = false;
        this.currentChannelIndex = 0;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("Телевизор включен. Текущий канал: " + channels.get(currentChannelIndex));
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Телевизор выключен.");
    }

    public void switchChannel(int channelNumber) {
        if (!isOn) {
            System.out.println("Телевизор выключен. Сначала включите его.");
            return;
        }

        for (int i = 0; i < channels.size(); i++) {
            if (channels.get(i).getNumber() == channelNumber) {
                currentChannelIndex = i;
                System.out.println("Переключено на канал: " + channels.get(currentChannelIndex));
                return;
            }
        }
        System.out.println("Канал с номером " + channelNumber + " не найден.");
    }

    @Override
    public String toString() {
        return "Television{" +
                "channels=" + channels +
                ", isOn=" + isOn +
                ", currentChannelIndex=" + currentChannelIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Television that = (Television) o;
        return isOn == that.isOn &&
                currentChannelIndex == that.currentChannelIndex &&
                Objects.equals(channels, that.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channels, isOn, currentChannelIndex);
    }
}

public class App {
    public static void main(String[] args) {
        List<Channel> channels = new ArrayList<>();
        channels.add(new Channel("Первый канал", 1, new Program("Новости", 8.5, 1000000)));
        channels.add(new Channel("Россия 1", 2, new Program("Фильм", 7.8, 500000)));
        channels.add(new Channel("ТНТ", 3, new Program("Шоу", 6.9, 300000)));

        Television tv = new Television(channels);

        tv.turnOn();
        tv.switchChannel(2);
        tv.switchChannel(4); // Несуществующий канал
        tv.turnOff();
    }
}