package eu.ase.reactstreams;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

class JSONData {
    private final String headline;
    private final LocalDate date;

    private JSONData(String headline, LocalDate date) {
        this.headline = headline;
        this.date = date;
    }

    public static JSONData of(String headline) {
        return new JSONData(headline, LocalDate.now());
    }

    public static JSONData create(String headline) {
        return new JSONData(headline, LocalDate.now());
    }

    public String getHeadline() {
        return headline;
    }

    public LocalDate getDate() {
        return date;
    }
}

class NewsSubscriber implements Flow.Subscriber<JSONData> {
    private static final int MAX_NEWS = 3;
    private int newsReceived = 0;
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.println("New subscription " + subscription);
        subscription.request(1);
    }

    @Override
    public void onNext(JSONData item) {
        System.out.println("News received " + item.getHeadline() + ", " + item.getDate());
        newsReceived++;
        if(newsReceived == MAX_NEWS) {
            System.out.println("News received, cancelling subscription");
            subscription.cancel();
            return;
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.printf("Error occured fetching news: %s\n", throwable.getMessage());
        throwable.printStackTrace(System.err);
    }

    @Override
    public void onComplete() {
        System.out.println("Fetched news completed");
    }
}

public class ProgMainReactiveStreams {
    public static void main(String[] args) {
        try(SubmissionPublisher<JSONData> newsPublisher = new SubmissionPublisher<>()) {
            NewsSubscriber newsSubscriber = new NewsSubscriber();
            newsPublisher.subscribe(newsSubscriber);

            List.of(JSONData.create("Important news"), JSONData.create("Some other news"),
                    JSONData.create("News")).forEach(newsPublisher::submit);

            while(newsPublisher.hasSubscribers()){

            }
            System.out.println("no more news subscribers left, closing publisher...");
        }
    }
}
