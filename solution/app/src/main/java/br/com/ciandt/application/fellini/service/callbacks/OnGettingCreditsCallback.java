package br.com.ciandt.application.fellini.service.callbacks;

import br.com.ciandt.application.fellini.domain.crew.Credit;

public interface OnGettingCreditsCallback {

    void onSuccess(Credit credit);

    void onError();
}
