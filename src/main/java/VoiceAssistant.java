import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class VoiceAssistant {

    public static void main(String[] args) throws Exception {
        // Конфигурация распознавания речи
        Configuration configuration = new Configuration();

        // Установка пути к ресурсам модели и файлам конфигурации
        configuration.setAcousticModelPath("src/main/resources/zero_ru.cd_cont_4000");
        configuration.setDictionaryPath("src/main/resources/dictionary.dic");
        configuration.setGrammarPath("src/main/resources");
        configuration.setGrammarName("voiceCommands");
        configuration.setUseGrammar(true); // Использование грамматики

        // Создание распознавателя речи
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        System.out.println("Голосовой ассистент запущен. Говорите команды...");

        // Запуск распознавания речи
        recognizer.startRecognition(true);

        // Обработка распознанных команд
        while (true) {
            String utterance = recognizer.getResult().getHypothesis();

            if (utterance.startsWith("эви открой браузер")) {
                openBrowser();
            } else if (utterance.startsWith("эви закрой браузер")) {
                closeBrowser();
            } else if (utterance.startsWith("эви открой вкладку")) {
                openNewTab();
            } else if (utterance.startsWith("эви закрой вкладку")) {
                closeCurrentTab();
            } else if (utterance.equals("эви обнови")) {
                refreshPage();
            } else if (utterance.equals("эви назад")) {
                goBack();
            } else if (utterance.equals("эви вперед")) {
                goForward();
            } else if (utterance.equals("эви вверх")) {
                scrollUp();
            } else if (utterance.equals("эви вниз")) {
                scrollDown();
            } else if (utterance.startsWith("эви открой стим")) {
                openSteam();
            } else if (utterance.startsWith("эви закрой стим")) {
                closeSteam();
            } else if (utterance.startsWith("эви открой дискорд")) {
                openDiscord();
            } else if (utterance.startsWith("эви закрой дискорд")) {
                closeDiscord();
            } else if (utterance.startsWith("эви открой ворд")) {
                openWord();
            } else if (utterance.startsWith("эви закрой ворд")) {
                closeWord();
            }
        }
    }

    private static void openBrowser() throws IOException {
        String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        Runtime.getRuntime().exec(chromePath);
        System.out.println("Открываю браузер...");
    }

    private static void closeBrowser() throws IOException {
        String chromeProcess = "chrome.exe";
        Runtime.getRuntime().exec("taskkill /f /im " + chromeProcess);
        System.out.println("Закрываю браузер...");
    }

    private static void openNewTab() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        System.out.println("Открываю новую вкладку...");
    }

    private static void closeCurrentTab() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        System.out.println("Закрываю текущую вкладку...");
    }

    private static void refreshPage() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
        System.out.println("Обновляю страницу или приложение...");
    }

    private static void goBack() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_ALT);
        System.out.println("Выполняю действие \"Назад\"...");
    }

    private static void goForward() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_ALT);
        System.out.println("Выполняю действие \"Вперед\"...");
    }

    private static void scrollUp() throws AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(-3);
        System.out.println("Выполняю действие \"Вверх\"...");
    }

    private static void scrollDown() throws AWTException {
        Robot robot = new Robot();
        robot.mouseWheel(3);
        System.out.println("Выполняю действие \"Вниз\"...");
    }

    private static void openSteam() throws IOException {
        String steamPath = "C:\\Program Files (x86)\\Steam\\Steam.exe";
        Runtime.getRuntime().exec(steamPath);
        System.out.println("Открываю Steam...");
    }

    private static void closeSteam() throws IOException {
        String steamProcess = "Steam.exe";
        Runtime.getRuntime().exec("taskkill /f /im " + steamProcess);
        System.out.println("Закрываю Steam...");
    }

    private static void openDiscord() throws IOException {
        String discordPath = "C:\\Users\\Shtigun\\AppData\\Local\\Discord\\app-1.0.9013\\Discord.exe";
        Runtime.getRuntime().exec(discordPath);
        System.out.println("Открываю Discord...");
    }

    private static void closeDiscord() throws IOException {
        String discordProcess = "Discord.exe";
        Runtime.getRuntime().exec("taskkill /f /im " + discordProcess);
        System.out.println("Закрываю Discord...");
    }

    private static void openWord() throws IOException {
        String wordPath = "C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE";
        Runtime.getRuntime().exec(wordPath);
        System.out.println("Открываю Word...");
    }

    private static void closeWord() throws IOException {
        String wordProcess = "WINWORD.EXE";
        Runtime.getRuntime().exec("taskkill /f /im " + wordProcess);
        System.out.println("Закрываю Word...");
    }
}
