package agentesl;
import com.google.*;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
public class Vale {
public static void main(String[] args) {
	String key = "AIzaSyBJ6i8JdjP7fHfJGLPU5AVf1tDYQEZMMHg";
	Translate tranlate = TranslateOptions.newBuilder().setApiKey(key).build().getService();
	Translation translation = tranlate.translate("Hola mamá, qué haces?", Translate.TranslateOption.targetLanguage("EN"), Translate.TranslateOption.sourceLanguage("ES"));
	String tr = translation.getTranslatedText();
	System.out.println(tr);
}
}
