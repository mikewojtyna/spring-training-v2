package pl.wojtyna.trainings.spring.crowdsorcery.asset;

import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;

public class AssetService {

    public void investIntoAsset(Investor investor, Money amount) {
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Asset> findAllAssets() {
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Asset> findAsset(String id) {
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Asset> findAllAssetsInvestedBy(Investor investor) {
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Asset> findAllFundraisedAssets() {
        throw new UnsupportedOperationException("Implement this method");
    }
}
