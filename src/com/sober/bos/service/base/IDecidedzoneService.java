package com.sober.bos.service.base;

import com.sober.bos.domain.Decidedzone;
import com.sober.bos.utils.PageBean;
import com.sober.bos.utils.PageBean;

/**
 * Created by Renhai on 2016/11/3.
 */
public interface IDecidedzoneService {
    public void pageQuery(PageBean pageBean);

    public void save(Decidedzone model, String[] subareaid);
}
