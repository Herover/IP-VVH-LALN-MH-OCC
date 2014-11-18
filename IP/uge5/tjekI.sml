fun erI (Cirkel ((cx, cy), r)) (x, y) =
        (x-cx)*(x-cx) + (y-cy)*(y-cy) <= r*r
    | erI (Rektangel ((x0, y0), (x1, y1))) (x,y) =
         x0 <= x andalso x <= x1 andalso
         y0 <= y andalso y <= y1;