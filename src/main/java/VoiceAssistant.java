import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class VoiceAssistant {

    private static WebDriver driver;
    private static String steamPath;
    private static String discordPath;
    private static String wordPath;

    public static void main(String[] args) throws Exception {
        // Конфигурация распознавания речи
        Configuration configuration = new Configuration();

        // Конфигурация пути к файлам
        loadFileConfiguration();
        // Установка пути к ресурсам модели и файлам конфигурации
        configuration.setAcousticModelPath("file:///C:/Users/Shtigun/Desktop/course2/voice-assistant/src/main/resources/ru");
        configuration.setDictionaryPath("file:///C:/Users/Shtigun/Desktop/course2/voice-assistant/src/main/resources/dictionary.dic");
        configuration.setGrammarPath("file:///C:/Users/Shtigun/Desktop/course2/voice-assistant/src/main/resources");
        configuration.setGrammarName("voiceCommands");
        configuration.setUseGrammar(true); // Использование грамматики

        // Создание распознавателя речи
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        System.out.println("Голосовой ассистент запущен. Говорите команды...");


        // Запуск распознавания речи
        recognizer.startRecognition(true);

        // Инициализация WebDriver
        System.setProperty("webdriver.chrome.driver", "C:/Users/Shtigun/Desktop/course2/voice-assistant/src/main/resources/chromedriver.exe");

        // Обработка распознанных команд
        while (true) {
            String utterance = recognizer.getResult().getHypothesis();

            if (utterance != null) {
                System.out.println("Распознанная команда: " + utterance);

                if (utterance.startsWith("otkroy brauzer")) {
                    driver = new ChromeDriver();
                    openBrowser();
                } else if (utterance.startsWith("zakkroy brauzer")) {
                    closeBrowser();
                } else if (utterance.startsWith("otkroy vkladku")) {
                    openNewTab();
                } else if (utterance.startsWith("zakkroy vkladku")) {
                    closeCurrentTab();
                } else if (utterance.equals("obnovi")) {
                    refreshPage();
                } else if (utterance.equals("nazad")) {
                    goBack();
                } else if (utterance.equals("vpered")) {
                    goForward();
                } else if (utterance.equals("vverkh")) {
                    scrollUp();
                } else if (utterance.equals("vniz")) {
                    scrollDown();
                } else if (utterance.startsWith("otkroy stim")) {
                    openSteam();
                } else if (utterance.startsWith("zakkroy stim")) {
                    closeSteam();
                } else if (utterance.startsWith("otkroy diskord")) {
                    openDiscord();
                } else if (utterance.startsWith("zakkroy diskord")) {
                    closeDiscord();
                } else if (utterance.startsWith("otkroy vord")) {
                    openWord();
                } else if (utterance.startsWith("zakkroy vord")) {
                    closeWord();
                } else if (utterance.equals("vklyuchi muziku")) {
                    playMusic();
                } else if (utterance.equals("vykluchi muziku")) {
                    pauseMusic();
                } else if (utterance.equals("vozobnovi")) {
                    resumeMusic();
                } else if (utterance.equals("sleduyushchiy")) {
                    playNextTrack();
                } else if (utterance.equals("predyushchiy")) {
                    playPreviousTrack();
                }
            }
        }
    }

    private static void loadFileConfiguration() {
        // Получение путей файлов
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("C:\\Users\\Shtigun\\Desktop\\course2\\voice-assistant\\src\\main\\resources\\config.properties")) {
            properties.load(input);

            // Чтение значений из файла конфигурации
            steamPath = properties.getProperty("steam.path");
            discordPath = properties.getProperty("discord.path");
            wordPath = properties.getProperty("word.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openBrowser() {
        // Открытие браузера
        driver.get("https://www.google.com");
        System.out.println("Браузер открыт.");
    }

    private static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Браузер закрыт.");
        } else {
            System.out.println("Браузер не был открыт.");
        }
    }

    private static void openNewTab() throws AWTException {
        // Открытие новой вкладки
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        System.out.println("Открываю новую вкладку...");
    }

    private static void closeCurrentTab() throws AWTException {
        // Закрытие текущей вкладки
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        System.out.println("Закрываю текущую вкладку...");
    }

    private static void refreshPage() throws AWTException {
        // Обновление страницы
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
        System.out.println("Обновляю страницу или приложение...");
    }

    private static void goBack() throws AWTException {
        // Переход назад
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_ALT);
        System.out.println("Выполняю действие \"Назад\"...");
    }

    private static void goForward() throws AWTException {
        // Переход вперед
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_ALT);
        System.out.println("Выполняю действие \"Вперед\"...");
    }

    private static void scrollUp() {
        // Прокрутка страницы вверх
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_UP);
            robot.keyRelease(KeyEvent.VK_PAGE_UP);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        System.out.println("Прокрутка вверх.");
    }

    private static void scrollDown() {
        // Прокрутка страницы вниз
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        System.out.println("Прокрутка вниз.");
    }

    private static void openSteam() {
        // Открытие Steam
        try {
            Runtime.getRuntime().exec(steamPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Steam открыт.");
    }

    private static void closeSteam() {
        // Закрытие Steam
        try {
            Runtime.getRuntime().exec("taskkill /f /im Steam.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Steam закрыт.");
    }

    private static void openDiscord() {
        // Открытие Discord
        try {
            Runtime.getRuntime().exec(discordPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Discord открыт.");
    }

    private static void closeDiscord() {
        // Закрытие Discord
        try {
            Runtime.getRuntime().exec("taskkill /f /im Discord.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Discord закрыт.");
    }

    private static void openWord() {
        // Открытие Word
        try {
            Runtime.getRuntime().exec(wordPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Word открыт.");
    }

    private static void closeWord() {
        // Закрытие Word
        try {
            Runtime.getRuntime().exec("taskkill /f /im winword.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Word закрыт.");
    }


    private static void playMusic() {
        // Играть воспроизведение музыки
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start yandexmusic://play/");
            processBuilder.start();
            System.out.println("Воспроизведение музыки...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pauseMusic() {
        // Пауза воспроизведения музыки
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start yandexmusic://pause/");
            processBuilder.start();
            System.out.println("Выключаю музыку...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void resumeMusic() throws AWTException {
        // Возобновление воспроизведения музыки
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start yandexmusic://playpause/");
            processBuilder.start();
            System.out.println("Возобновляю музыку...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void playNextTrack() {
        // Воспроизведение следующего трека
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start yandexmusic://next/");
            processBuilder.start();
            System.out.println("Включаю следующий трек...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void playPreviousTrack() {
        // Воспроизведение предыдущего трека
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start yandexmusic://previous/");
            processBuilder.start();
            System.out.println("Включаю предыдущий трек...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exit() {
        // Завершение работы ассистента
        System.out.println("Выход из голосового ассистента.");
        System.exit(0);
    }
}
