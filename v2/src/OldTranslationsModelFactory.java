public class OldTranslationsModelFactory {
    private static OldTranslationsModel model;


    public static OldTranslationsModel getModel()
    {
        if (model == null)
        {
            model = new OldTranslationsModel();
        }
        return model;
    }
}
