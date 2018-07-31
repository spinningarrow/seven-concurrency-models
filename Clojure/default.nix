let pkgs = import <nixpkgs> {};

in pkgs.stdenv.mkDerivation rec {
  name = "seven-concurrency-models-clojure";

  buildInputs = with pkgs; [
    leiningen
  ];
}
