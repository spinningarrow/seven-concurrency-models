let pkgs = import <nixpkgs> {};

in pkgs.stdenv.mkDerivation rec {
  name = "FunctionalProgramming";

  buildInputs = with pkgs; [
    leiningen
  ];
}
