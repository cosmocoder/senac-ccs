package br.com.senac.ccs.thinkfast;

import org.springframework.web.context.request.async.DeferredResult;

public class WebScreen implements Screen {

    private DeferredResult<Result> deferredResult;

    public WebScreen() {
        this.deferredResult = new DeferredResult<Result>();
    }

    @Override
    public void show(Result result) {
        deferredResult.setResult(result);
    }

    public DeferredResult getDeferredResult() {
        return deferredResult;
    }
}
