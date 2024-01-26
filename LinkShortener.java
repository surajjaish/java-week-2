import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public LinkShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shortenLink(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    public String getOriginalLink(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "Short URL not found");
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            shortUrl.append(CHARACTERS.charAt(randomIndex));
        }

        return shortUrl.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        String longUrl1 = "https://www.example.com";
        String shortUrl1 = linkShortener.shortenLink(longUrl1);
        System.out.println("Shortened URL for " + longUrl1 + ": " + shortUrl1);

        String longUrl2 = "https://www.example2.com";
        String shortUrl2 = linkShortener.shortenLink(longUrl2);
        System.out.println("Shortened URL for " + longUrl2 + ": " + shortUrl2);

        String shortUrl1Original = linkShortener.getOriginalLink(shortUrl1);
        System.out.println("Original URL for " + shortUrl1 + ": " + shortUrl1Original);

        String shortUrlNotFound = "https://short.url/notfound";
        String notFoundOriginal = linkShortener.getOriginalLink(shortUrlNotFound);
        System.out.println("Original URL for " + shortUrlNotFound + ": " + notFoundOriginal);
    }
}
